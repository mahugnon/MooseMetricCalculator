"
I'm the component that group a dropdown and a label
"
Class {
	#name : #MMCDropListPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'dropList',
		'label',
		'selected'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCDropListPresenter class >> defaultSpec [
	^ SpecTableLayout row
		minCellSize: 10;
		maxCellSize: 20;
		add: #label;
		add: #dropList;
		yourself
]

{ #category : #examples }
MMCDropListPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #accessing }
MMCDropListPresenter >> dropList [
	^ dropList
]

{ #category : #initialization }
MMCDropListPresenter >> initializePresenter [
	dropList
		whenSelectedItemChanged: [ :item | selected:=item]
]

{ #category : #initialization }
MMCDropListPresenter >> initializeWidgets [
	dropList := self newDropList.
	dropList
		
		displayBlock: [ :item | item name asString ].
	label := self newLabel
]

{ #category : #accessing }
MMCDropListPresenter >> label [
	^ label
]

{ #category : #accessing }
MMCDropListPresenter >> label: aString [
	label label: aString
]

{ #category : #accessing }
MMCDropListPresenter >> selected [
	^ selected
]

{ #category : #updating }
MMCDropListPresenter >> update [
	dropList items: context getProperties
]
